from app.website import constants as WEB
from openopt import TSP
import requests
import networkx as nx

def tsp(locations):

    G = nx.Graph()

    address_list = [location[1] for location in locations]
    address_list = '|'.join(address_list)

    url = WEB.DISTANCE % (address_list, address_list)
    response = requests.get(url)
    if response.status_code != 200:
        return None

    i = 0
    for location in locations:
        G.add_node(i, name=location[0], addr=location[1])
        i = i + 1

    json = response.json()
    rows = json["rows"]
    i = 0
    for row in rows:
        elements = row["elements"]
        j = 0
        for element in elements:
            if i == j: continue
            distance = element["distance"]["value"]
            G.add_edge(i, j, weight=distance)
            j = j + 1
        i = i + 1

    p = TSP(G, objective='weight')
    r = p.solve('sa')

    data = [{"name":locations[a][0], "addr":locations[a][1]}
                for (a, b, dist) in r.Edges]
    return data

