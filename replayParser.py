# Usage: python replayParser.py "/path/to/WCS 2014 Season 2 Replays/"

import json
import sys
import os
import string

import spawningtool.parser

def create_expected_results(filename):
    outputFile = os.path.basename(filename)
    outputFile = string.replace(outputFile, ".SC2Replay", ".json")
    with open(outputFile, 'w') as fout:
        results = spawningtool.parser.parse_replay(filename)
        json.dump(results, fout)


def pp(arg, dirname, names):
    for f in names:
        replay = os.path.join(dirname,f)
        if os.path.isfile(replay):
            print(replay)
            create_expected_results(replay)


d = os.path.dirname(str(sys.argv[1]))
os.path.walk(d, pp, 1)
