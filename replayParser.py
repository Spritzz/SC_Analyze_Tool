# Usage: python replayParser.py "/path/to/WCS 2014 Season 2 Replays/"

import json
import sys
import os
import string
import argparse
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


def main():
    """
    Execute Replay Parser
    """
    parser = argparse.ArgumentParser()
    parser.add_argument('directory', help='parent directory containing replay files')

    args = parser.parse_args()

    if args.directory:
        d = os.path.dirname(args.directory)
        os.path.walk(d, pp, 1)


if __name__ == '__main__':
    main()
