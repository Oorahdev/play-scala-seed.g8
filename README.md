# play-scala-seed.g8

Giter8 template for generating a Play project seed in Scala customized for Oorah


## Running

If you want to create a project:

```bash
sbt new oorahdev/play-scala-seed.g8
```
### Features?

- Plugins
  - Play plugin
  - Docker plugin
  - Ecr plugin
  - Swagger plugin
  - Scalafmt plugin
- Filters
  - Logger
  - Prometheus metrics
  
### Use
 
```bash
sbt clean compile swagger docker:publishLocal ecr:push
```


  

