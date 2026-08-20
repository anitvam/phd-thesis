mas {
    environment { /* external actions' definitions here */}
    agent("pinger") { /* pinger specification here */ }
    agent("ponger") { /* ponger specification here */ }
    executionStrategy { oneThreadPerAgent() } // first-class support
}.start()