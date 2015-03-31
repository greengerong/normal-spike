import sys
import os

class ProcessHandler():
    def __init__(self):
        self.files = ["../"];

    def set(self, params):
        for item in self.files:
            os.system('cd {1};mvn versions:set -DnewVersion={0}'.format(params[0], item))
    def revert(self, paramsp):
        for item in self.files:
            os.system('cd {0};mvn versions:revert'.format(item))
    def commit(self, params):
        for item in self.files:
            os.system('cd {0};mvn versions:commit'.format(item))

if __name__ == '__main__':
   handler = sys.argv[1]
   params = sys.argv[2:]
   process_handler = ProcessHandler()
   attr = getattr(process_handler, handler)
   attr(params);