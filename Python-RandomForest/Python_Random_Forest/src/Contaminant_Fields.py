'''
Created on Nov 30, 2020

@author: Wmelende
'''

def split_contaminant_fields(df, nrow, col_names):
    # Process the contaminant fields     
    subs1 = "contam"
    list_contaminants = [icol for icol in col_names if subs1 in icol]
    num_contaminants = len(list_contaminants)
    contaminants_set = set()
    
    for irow in range(0, nrow):
        for icol in range(0, num_contaminants):       
            if (df[list_contaminants[icol]].iloc[irow].strip() == "NULL"):            
                continue       
            else:            
                list_str = df[list_contaminants[icol]].iloc[irow].split(":")            
                if list_str[1].strip().lower() in contaminants_set:
                    if (list_str[0] != ''):
                        strnum = list_str[1].strip().lower() + ' num'
                        df.loc[irow, strnum] = int(list_str[0])
                        
                    if (list_str[2] != ''):
                        strvalue = list_str[1].strip().lower()
                        df.loc[irow, strvalue] = float(list_str[2])
                    
                    if (list_str[3] != ''):
                        strunits = list_str[1].strip().lower() + ' unit'
                        df.loc[irow, strunits] = list_str[3]
                        
                    if (list_str[4] != ''):
                        strnonnum = list_str[1].strip().lower() + ' method'
                        df.loc[irow, strnonnum] = list_str[4]
                                    
                else:
                    contaminants_set.add(list_str[1].strip().lower())
                    strvalue = list_str[1].strip().lower()
                    strunits = list_str[1].strip().lower() + ' unit'
                    strnum = list_str[1].strip().lower() + ' num'
                    strnonnum = list_str[1].strip().lower() + ' method'
                    
                    if (list_str[0] != ''):                    
                        df.loc[irow, strnum] = int(list_str[0])
                    else:
                        df.loc[irow, strnum] = ''
                        
                    if (list_str[2] != ''):
                        df.loc[irow, strvalue] = float(list_str[2])
                    else:
                        df.loc[irow, strvalue] = ''
                                        
                    if (list_str[3] != ''):
                        df.loc[irow, strunits] = list_str[3]
                    else:
                        df.loc[irow, strunits] = ''
                        
                    if (list_str[4] != ''):
                        df.loc[irow, strnonnum] = list_str[4]
                    else:
                        df.loc[irow, strnonnum] = ''
