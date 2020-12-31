'''
This module contains a function that writes a DataFrame to a CSV file.

Created on Dec 21, 2020

@author: Wmelende
'''

from pathlib import Path

def write_to_csv(df, invitro_output):
    '''
    Name
    ----
    write_to_csv
    
    Description
    -----------
    This function writes a DataFrame to a CSV file.
    
    Input Parameters
    ----------------
    df : DataFrame
        DataFrame containing the in vitro rows.
    
    '''
    if not Path(invitro_output).exists():
        invitro_output = "..\\" + invitro_output
        
    # Write DataFrame to output.
    # Note that we must specify the right type of encoding to write out all characters correctly.
    # Some of the data contain Greek letters which need to be accounted for when writing to a CSV file.
    df.to_csv(invitro_output, encoding = 'utf-8-sig')
    
    # Print message to console indicating that writing to CSV has completed.
    print("Writing of " + invitro_output + " to a CSV file has completed.")