'''
Created on Dec 3, 2020

@author: Wmelende
'''

import pandas as pd
from Additive_Fields import split_additive_fields
from pathlib import Path

def test_parameters_fields():
 
    input_file = "data\\Test_Data.csv"
    if not Path(input_file).exists():
        input_file = "..\\data\\Test_Data.csv"
    
    # Read CSV file.
    dft = pd.read_csv(input_file, na_values = "NULL", skip_blank_lines = False, keep_default_na = True, na_filter = False, low_memory = False)
    
    # Select in vitro rows only.
    # Lowercase and remove extra white space from the strings before selecting the in vitro rows.
    dft["assayType"] = dft["assayType"].str.lower().str.strip()
    dft = dft[dft.assayType == 'in vitro']
    
    # Reset the rows indices.
    dft = dft.reset_index(level = 0, drop = True) 
    
    # Set all NULLs to None.
    dft = dft.replace({'NULL': None})
    
    # Extract column names.
    col_names = list(dft.columns)
    
    # Determine number of rows in data frame.
    nrow = len(dft.index)
    
    # Proceed to split up the concatenated fields.
    split_additive_fields(dft, nrow, col_names)
    
    # Assert selected values
    assert dft['fetal bovine serum'][1] == 10
    assert dft['fetal bovine serum unit'][1] == 'percent'
    assert dft['tryptone'][1] == 47
    assert dft['tryptone unit'][1] == 'milligram/liter'
    assert dft['ammonium sulfate'][1] == 116
    assert dft['ammonium sulfate'][8] == 116