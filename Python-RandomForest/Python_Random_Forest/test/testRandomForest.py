'''
Created on Jun 22, 2020

@author: pharten
'''
import unittest

import RandomForest as rf

from pathlib import Path

class TestRandomForest(unittest.TestCase):
    
    def test_readcsv1(self):
    	filename = "temp"
    	path = Path(filename)
    	if path.exists():
    		if path.is_file(): path.remove()
    		elif path.is_dir(): path.rmdir()
    	self.assertIsNotNone(rf.readcsv(self, filename)," file does not exist")
    	path.mkdir()
    	self.assertIsNotNone(rf.readcsv(self, filename)," file is a directory")
    	path.rmdir()

    def test_readcsv2(self):
    	path = Path.cwd();
    	if path.name == "test":
    		path = path.joinpath("../data/LC50_training_set-2d.csv")
    	else:
    		path = path.joinpath("data/LC50_training_set-2d.csv")
    	train_features = rf.readcsv(self, path)
    	shape = train_features.shape
    	self.assertEqual(shape[0], 659,  " training set has wrong number of rows")
    	self.assertEqual(shape[1], 799,  " training set has wrong number of columns")
        

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()