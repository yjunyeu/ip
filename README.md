# Waty User Guide
![Screenshot of Ui](Ui.png)

## Introduction
Waty is a Personal Assistant Chatbot that helps you create and track different types
of tasks: ToDos, Deadlines and Events. Waty also allow you to search, sort and 
organise your tasks efficiently.

## Getting Started
To launch Waty, run this command in the terminal:
```
java -jar waty.jar
```

## Features
1. Add Tasks: Add ToDo, Deadline, or Event tasks.
2. Mark/ Unmark Tasks: Mark tasks as done or undone.
3. Delete Tasks: Remove tasks from the list.
4. Find Tasks: Search for tasks by keyword.
5. Sort Tasks: Arrange tasks chronologically by date.
6. Data Persistence: Automatically saves tasks to a .txt file and loads them upon startuo.
7. Error Handling: Shows errors with red-coloured messages when invalid commands are entered.

## Commands
<table>
  <thead>
    <tr>
      <th>Command</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><code>todo [task description]</code></td>
      <td>Adds a ToDo task</td>
    </tr>
    <tr>
      <td><code>deadline [task description] /by [yyyy-MM-dd HHmm]</code></td>
      <td>Adds a Deadline task</td>
    </tr>
    <tr>
      <td><code>event [task description] /from [yyyy-MM-dd HHmm] /to [yyyy-MM-dd HHmm]</code></td>
      <td>Adds an Event task</td>
    </tr>
    <tr>
      <td><code>list</code></td>
      <td>Lists all tasks</td>
    </tr>
    <tr>
      <td><code>mark [task number]</code></td>
      <td>Marks a task as done</td>
    </tr>
    <tr>
      <td><code>unmark [task number]</code></td>
      <td>Marks a task as not done</td>
    </tr>
    <tr>
      <td><code>delete [task number]</code></td>
      <td>Deletes a task</td>
    </tr>
    <tr>
      <td><code>find [keywords...]</code></td>
      <td>Finds tasks by keyword</td>
    </tr>
    <tr>
      <td><code>sort</code></td>
      <td>Sort all tasks by chronological order</td>
    </tr>
    <tr>
      <td><code>bye</code></td>
      <td>Exits the program</td>
    </tr>
  </tbody>
</table>


## Requirements
Java 11 or higher

## File Management
Tasks are saved to a file named `waty.txt` in a `data` folder.  
You can delete this file to clear your tasks.
