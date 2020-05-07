# Fever Tracker
SER210 Spring 2020 Completed Project
Neel Bains, Timothy Carta, Ryan Hayes

Fever Tracker is an Android application that allows the user to keep track of their temperature.
The user can either add a new record or view records that were previously entered.

Adding a record allows the user to input:
- Temperature 
- Date
- Time 
- How they're feeling on a scale of 1 to 10

If an entered temperature is too dangerous, then the user will be prompted with a warning.

Viewing previous records will display every record from top to bottom in a list. The entries are stored and retrieved
through an SQLite database. Entries can be selected and shared with a doctor through the Android toolbar.
