# lppneu

*exdending pneu for LPPNs (Logic Programming Petri Nets)*

LPPN integrates Logic Programming constructs with Petri Nets. The verb/noun categories are mapped as labelings of transitions/places. Tokens are grounded statements, consumed/produced by events.

(still quite a prototype)

##Dependencies

* https://github.com/s1l3n0/pneu

## Components

* LPPN model (labels on places, transitions, tokens with grounded predicates)
* LPPN simulator (via pneu) 
* LPPN analyzer (via pneu)
* LPPN exporter (via pneu)
* parser of LPPN files (.lppn)

## Usage Examples

**Logic Programming Petri net (LPPN) creation:**
```
Net net = new LPNet()

Transition tIn = net.createEmitterTransition()
Transition tOut = net.createCollectorTransition()

Place pIn = net.createPlace("object(Object)")
Transition tAction = net.createTransition("cutsInTwoPieces(Person, Object)")
Place pOut1 = net.createPlace("object(leftPiece(Object))")
Place pOut2 = net.createPlace("object(rightPiece(Object))")

net.createArc(tIn, pIn)
net.createArc(pIn, tAction)
net.createArc(tAction, pOut1)
net.createArc(tAction, pOut2)
net.createArc(pOut1, tOut)
net.createArc(pOut2, tOut)

net.resetIds() 
```


**monolithic simulation**
```
NetRunner runner = new NetRunner()
runner.load(net)
runner.run(2) // to make 2 discrete steps
```

**json export**
```
net.exportToJson("readmeNet")
```

output: /out/json/readmeNet.json
```
{
  "places": [
    {"id": "p0", "label": "object(Object)"},
    {"id": "p1", "label": "object(leftPiece(Object))", "marking": ["object(leftPiece(Object:_p0object0))"]},
    {"id": "p2", "label": "object(rightPiece(Object))", "marking": ["object(rightPiece(Object:_p0object0))"]}
  ],
  "transitions": [
    {"id": "t0", "link": true},
    {"id": "t1", "link": true},
    {"id": "t2", "label": "cutsInTwoPieces(Person, Object)"}
  ],
  "arcs": [
    { "source": "t1", "target": "p0" },
    { "source": "p0", "target": "t2" },
    { "source": "t2", "target": "p1" },
    { "source": "t2", "target": "p2" },
    { "source": "p1", "target": "t0" },
    { "source": "p2", "target": "t0" }
  ]
} 
```

**analysis**
```
NetRunner runner = new NetRunner()
runner.load(net)
runner.analyse()
runner.analysis.exportToLog("readmeNet")
```

output: /out/log/analysis/readmeNet.analysis.log
```
Summary: 
0: t0.*(Object:_t0object0), t1.cutsInTwoPieces(Person:_t1person1, Object:_t0object0), t2.* (t2).

Stories: 
(st0) -- [t0:*(Object:_t0object0)] -- (st1) -- [t1:cutsInTwoPieces(Person:_t1person1, Object:_t0object0)] -- (st2) -- [t2] -- (st0)

States: 
st0: [p0 (0), p1 (0), p2 (0)] / [t0:*(Object:_t0object0) => (st1)] 
st1: [p0 (1), p1 (0), p2 (0)] / [t1:cutsInTwoPieces(Person:_t1person1, Object:_t0object0) => (st2)] 
st2: [p0 (0), p1 (1), p2 (1)] / [t2 => (st0)] 
```

## Contributing

1. Fork it
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push to the branch (`git push origin my-new-feature`)
5. Create new Pull Request