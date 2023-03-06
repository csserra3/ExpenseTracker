# Expenses Tracker 

## Track expenses and categorize by type of expense 

The application will track expenses and categorize them by type. Everytime an expense is entered, the tracker will keep 
track of:
- title of expense 
- the amount spent
- the category of expense

Additionally, a **spending limit** could be set for each category, so that a warning message prints when it nears that 
limit. I will be able to use this application to track my own spending habits. This will help me identify my spending 
patterns and help with budgeting while in school. The application could also be useful for other students that are 
interested in tracking their expenses. 

## User Stories
- As a user, I want to be able to add my expense to my tracker.
- As a user, I want to be able to see the titles for expenses in a category.
- As a user, I want to be able to see the total sum of expenses per category.
- As a user, I want to be able to see the total sum of expenses in all categories combined.
- As a user, I want to be able to save my entered expenses to file.
- As a user, I want to be able to load my entered expenses from file, and continue where I left off.

## Phase 4: Task 2
C:\Users\Serra\.jdks\corretto-1.8.0_312\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2021.3.1\lib\idea_rt.jar=56770:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2021.3.1\bin" -Dfile.encoding=UTF-8 -classpath C:\Users\Serra\.jdks\corretto-1.8.0_312\jre\lib\charsets.jar;C:\Users\Serra\.jdks\corretto-1.8.0_312\jre\lib\ext\access-bridge-64.jar;C:\Users\Serra\.jdks\corretto-1.8.0_312\jre\lib\ext\cldrdata.jar;C:\Users\Serra\.jdks\corretto-1.8.0_312\jre\lib\ext\dnsns.jar;C:\Users\Serra\.jdks\corretto-1.8.0_312\jre\lib\ext\jaccess.jar;C:\Users\Serra\.jdks\corretto-1.8.0_312\jre\lib\ext\jfxrt.jar;C:\Users\Serra\.jdks\corretto-1.8.0_312\jre\lib\ext\localedata.jar;C:\Users\Serra\.jdks\corretto-1.8.0_312\jre\lib\ext\nashorn.jar;C:\Users\Serra\.jdks\corretto-1.8.0_312\jre\lib\ext\sunec.jar;C:\Users\Serra\.jdks\corretto-1.8.0_312\jre\lib\ext\sunjce_provider.jar;C:\Users\Serra\.jdks\corretto-1.8.0_312\jre\lib\ext\sunmscapi.jar;C:\Users\Serra\.jdks\corretto-1.8.0_312\jre\lib\ext\sunpkcs11.jar;C:\Users\Serra\.jdks\corretto-1.8.0_312\jre\lib\ext\zipfs.jar;C:\Users\Serra\.jdks\corretto-1.8.0_312\jre\lib\jce.jar;C:\Users\Serra\.jdks\corretto-1.8.0_312\jre\lib\jfr.jar;C:\Users\Serra\.jdks\corretto-1.8.0_312\jre\lib\jfxswt.jar;C:\Users\Serra\.jdks\corretto-1.8.0_312\jre\lib\jsse.jar;C:\Users\Serra\.jdks\corretto-1.8.0_312\jre\lib\management-agent.jar;C:\Users\Serra\.jdks\corretto-1.8.0_312\jre\lib\resources.jar;C:\Users\Serra\.jdks\corretto-1.8.0_312\jre\lib\rt.jar;C:\Users\Serra\Documents\cpsc210\project_g0u9q\out\production\Project-Starter;C:\Users\Serra\Documents\cpsc210\project_g0u9q\lib\json-20210307.jar ui.Main
Thu Mar 31 23:12:54 PDT 2022
Added expense: $5, coffee
Thu Mar 31 23:13:06 PDT 2022
Added expense: $10, poke bowl
Thu Mar 31 23:13:20 PDT 2022
Added expense: $200, shopping

Process finished with exit code 0

## Phase 4: Task 3
Reflection: 
- Instead of an image appearing and staying when a button is pressed, I could flash it on screen.
- Some methods are still quite long, I could refactor and use helper methods. 
- For example the processCommand() method uses many cases, maybe the switch cases can be its own method.
- Overall visual look of the GUI could be improved! Even if it's for visual aesthetics not functionality.
- Use more constants for GUI for sizing the panels or frames so I won't have to individually change dimensions per method. 
