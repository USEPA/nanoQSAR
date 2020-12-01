'''
Created on Nov 30, 2020

@author: Wmelende
'''

def split_result_fields(df, nrow, col_names):
    # Process the result fields                  
    subs3 = "result"
    list_results = [icol for icol in col_names if subs3 in icol]
    num_results = len(list_results)
    list_str_result = []
    string_results1 = []
    
    for i in range(0, num_results):
        list_str_result.append(list_results[i].split("_"))
    
    for i in range(0, num_results):
        string_results = []
        string_results.append(list_str_result[i][0] + ' ' + list_str_result[i][1]) 
        string_results.append(list_str_result[i][0] + ' ' + list_str_result[i][2])
        string_results.append(list_str_result[i][0] + ' ' + list_str_result[i][3])
        string_results.append(list_str_result[i][0] + ' ' + list_str_result[i][4])
        string_results.append(list_str_result[i][0] + ' ' + list_str_result[i][5])
        string_results.append(list_str_result[i][0] + ' ' + list_str_result[i][6])
        string_results.append(list_str_result[i][0] + ' ' + list_str_result[i][7])
        string_results.append(list_str_result[i][0] + ' ' + list_str_result[i][8])
        string_results.append(list_str_result[i][0] + ' ' + list_str_result[i][9])
        string_results1.append(string_results)
    
    for irow in range(0, nrow):
        for icol in range(0, num_results):
            if (df[list_results[icol]].iloc[irow].strip() == "NULL"):
                for i in range(0, 9):
                    df.loc[irow, string_results1[icol][i]] = '' 
            else:
                list_str = df[list_results[icol]].iloc[irow].split(":")
                if (list_str[0] != ''):
                    df.loc[irow, string_results1[icol][0]] = int(list_str[0])
                else:
                    df.loc[irow, string_results1[icol][0]] = ''
                if (list_str[1] != ''):
                    df.loc[irow, string_results1[icol][1]] = list_str[1]
                else:
                    df.loc[irow, string_results1[icol][1]] = ''
                if (list_str[2] != ''):
                    df.loc[irow, string_results1[icol][2]] = list_str[2]
                else:
                    df.loc[irow, string_results1[icol][2]] = ''
                if (list_str[3] != ''):
                    df.loc[irow, string_results1[icol][3]] = float(list_str[3])
                else:
                    df.loc[irow, string_results1[icol][3]] = ''
                if (list_str[4] != ''):
                    df.loc[irow, string_results1[icol][4]] = list_str[4]
                else:
                    df.loc[irow, string_results1[icol][4]] = ''
                if (list_str[5] != ''):
                    df.loc[irow, string_results1[icol][5]] = list_str[5]
                else:
                    df.loc[irow, string_results1[icol][5]] = ''
                if (list_str[6] != ''):
                    df.loc[irow, string_results1[icol][6]] = list_str[6]
                else:
                    df.loc[irow, string_results1[icol][6]] = ''
                if (list_str[7] != ''):
                    df.loc[irow, string_results1[icol][7]] = float(list_str[7])
                else:
                    df.loc[irow, string_results1[icol][7]] = ''
                if (list_str[8] != ''):
                    df.loc[irow, string_results1[icol][8]] = float(list_str[8])
                else:
                    df.loc[irow, string_results1[icol][8]] = ''