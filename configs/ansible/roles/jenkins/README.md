Jenkins contains pipelines, plugins and hooks. The hooks are written on groovy scripts and they change some jenkins behavior and plugins behavior. 

If you want to change scripts you should mark pom.xml as a maven project then all dependencies will be loaded.

The last step is creating of a docker image and a docker container. You must mount directories with scripts, pipelines, gdsl, plugin.txt to docker container or copy to docker image.