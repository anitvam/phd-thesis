# phidias/lib/phidias/Runtime.py
import threading
class Runtime:
    # ...
    @classmethod
    def run_agent(cls, a):
        e = cls.engines[a]
        t = threading.Thread(target=e.run)
        t.start()