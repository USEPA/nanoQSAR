'''
Created on Dec 3, 2020

@author: Wmelende
'''

import pandas as pd
from Result_Fields import split_result_fields

def test_parameters_fields():
    
    input_file = "C:\\Users\\wmelende\\git\\nanoQSAR\\Python-RandomForest\\Python_Random_Forest\\test\\Test_Data.csv"
    
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
    split_result_fields(dft, nrow, col_names)
    
    # Assert selected values
    assert dft['result01 type'][0] == 'front scatter'
    assert dft['result01 val'][0] == 1
    assert dft['result01 unit'][0] == 'ratio to control'
    assert dft['result02 type'][1] == 'side scatter'
    assert dft['result02 val'][1] == 1.28
    assert dft['result02 unit'][1] == 'ratio to control'