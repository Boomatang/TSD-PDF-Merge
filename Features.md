# Requested Features

## GA Detailing.
The current way the the GA drawings are marge does not work well. This has to do with how the GA tag is not handled. With the first version the GA drawings were not that big of a deal, but now that is not the case and they are been used how TSD intended them to be used. 

Now in the last project there has been two sets of GA drawings done. The first set started with "S" and the second set had "G". Now the letters that were used may have been better if the second set used the "S" but this would only be for the users own use and have no affect to the merged results in the end. 

Now the plan is to spilt the files in to groups that can be merged as one. So for the above example all the "S" files would be in one document and the "G" in a different document. These documents should follow a standard format to the section drawings that are been done. 

> A file that is to be merged has a format that looks like : *XXXX-GA-X-###-DRAWING TITLE_X*
> The saved merged file should have the name format of : *XXXX-GA-X (YYYY-MM-DD)*

The GA is the only contest that there is in the files to be merged and the output file names. Knowing this we can phrase the input files to find this GA tag. After the tag has been found we can get the class of file that it is. This is the "S" and "G" that was used before. The only thing that we know about this class is that it should be between *-* in the file name but this can be changed by the user  in TSD. It will for now have to bee taken that all the files will follow this format. The last numbers are also done by t he user in TSD but TSD does not let the user use the same file names so it should make number unique to that class of drawing. The Drawing title does not have to be excluded in the file name but it helps when there is dwg files save of the same drawings, this is a function that is built into TSD. _X is the revision number and the issue that was been had with this and the drawing title s should have been fixed in the last update.

Like all the other PDF's that are merged the this should put the most current revision of the file in numeric order in side the merged file. 

## Section Classifying.
This feature needs some thinking about to even explain what is been tried.

## Listing of files used
This in time will become a cover sheet for the drawings that will use the same style template as the drawing sheets. When it gets to that stage there will need to be the option to add the cover sheet to the fully merged files or use in as a standalone file.
 
For now this will just be a formatted text file that can be used to check of that all the required files have been merged. This more than likely will not be sent to the customer but used in house for checking only.

There is a number of points that need to be looked at before any work can be start on this feature. Below we will decide on the function that this feature should provide. I will be talking about this as if it is the full function that is creating a pdf file as the output.

- What information will be displayed?
- Will each merged file have its own content file or will there just be one content file for that day?
- What other information should be used in this, such as the client contact details?
- If the client details need to go into the file how would this be gone about?

**What information will be displayed?**
Given that the file name is the only information that is given about the contains this limits what information can be displayed. We know that the file is the drawing number with information appended to it. Some of this is information is the revision number this can be found on all drawings and should be included. On the GA drawings the drawing title do be added to the file name. For the file we could get the date on which the pdf was created, this would not be the same as the date of the revision. 

If we just use the drawing number for the document names and the revision that is included in the merged document, this will let the user know see what they have. Include the date that the file was export to pdf could be help if there is a file that was last exported to pdf weeks before, or if there seems to be a difference in two files of the version. This information should be displayed in columns with a header on each page if more than one page was required.
 
Other information that could be included is the number of drawings that was merged to start with, This would not allow for multi page pdfs  but would give a good overview to the contains of the merged file.
