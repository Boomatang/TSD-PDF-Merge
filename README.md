#TSD PDF Merge
TSD PDF Merge is a simple that is used to combine the outputted pdf files form the solidworks plugin TSD. 

##How to use.
To use the tool launch the program. Select the folder you wish to run the merge on. Then press start merge.

There is a few things that should a user should. The tool goes through all the folders in the folder you select. This means the top level pdf folder can be selected and the tool will find all the pdf's that are below this. At the 
same time the program will check to see what is the most up-to-date files. It does this by checking the revision letters that are appended to the end of the file name. If you use numbers instead of letters there should be no 
problem but it has not been checked. 

If the search finds 2 files with the same revision number that have been exported out on different dates, it will pick the newer file. 

After the program is after running there is a folder called COMBINE created in the Pdf's folder. This folder has the merged files. The files are stored by date of merge and then WBS. As plates, sections and vendor items are common 
across multiple area's These are filed under Global. All plates, sections and vendor items get merged into their own file which as example is called "PL (YYYY-MM-DD).pdf" this is the same for each type. The welded members of the 
areas get broken down in to their discipline. so all the BR's will be together and the CM's will be together. 

It is not know how this will handle custom naming, its still in a very earlier stage of life.

## Version History
<<<<<<< HEAD
### 0.1.1
- Fix to Combined folder been used.
- Fix to revison number fault added.
- Fix starting below Pdf's folder bug.


=======
>>>>>>> master
### 0.1-Bata
- Basic  function added.

