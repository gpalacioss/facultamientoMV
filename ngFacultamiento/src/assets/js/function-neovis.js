var viz;
function draw() {
  var config = {
    container_id: "viz",
    server_url: "bolt://localhost:7687",
    server_user: "neo4j",
    server_password: "sorts-swims-burglaries",
    labels: {
      //"Character": "name",
      "Character": {
        "caption": "name",
        "size": "pagerank",
        "community": "community"
        //"sizeCypher": "MATCH (n) WHERE id(n) = {id} MATCH (n)-[r]-() RETURN sum(r.weight) AS c"
      }
    },
    relationships: {
      "INTERACTS": {
        "thickness": "weight",
        "caption": false
      }
    },
    initial_cypher: "MATCH (n)-[r:INTERACTS]->(m) RETURN n,r,m"
  };
  viz = new NeoVis.default(config);
  viz.render();
  console.log(viz);
}
