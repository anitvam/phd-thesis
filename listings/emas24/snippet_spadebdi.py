# spade_bdi/bdi.py
from spade.agent import Agent
class BDIAgent(Agent):
   # ...

# spade/agent.py
from spade.container import Container
class Agent(object):
    def __init__(self, ...):
        # ...
        self.container = Container()
        # ...
        self.loop = self.container.loop
        # ...

# spade/container.py
class Container(object):
    def __init__(self):
        # ...
        self.loop = get_or_create_eventloop() # uses Python's asyncio API
        # ...
    
    def run(self, coro: Awaitable) -> None:
        self.loop.run_until_complete(coro)