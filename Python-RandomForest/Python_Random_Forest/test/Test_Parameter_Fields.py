'''
Created on Dec 2, 2020

@author: Wmelende
'''

import pandas as pd
from Parameters_Fields import split_parameters_fields
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
    split_parameters_fields(dft, nrow, col_names)
    
    # Assert values
    assert dft['happel model parameter'][1] == 29.91
    assert dft['column length'][7] == 30
    assert dft['column length unit'][7] == 'centimeter'
    assert dft['duration incubation'][0] == 24
    assert dft['duration incubation unit'][0] == 'hours'
    assert dft['duration incubation'][7] == 24
    assert dft['duration incubation unit'][7] == 'hours'
    assert dft['ph'][6] == 7.34
    assert dft['ph unit'][6] == 'pH'
    assert dft['distance'][4] == 25
    assert dft['distance unit'][0] == 'centimeters'
    