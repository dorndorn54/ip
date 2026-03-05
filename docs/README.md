# Dorn - Task Manager Chatbot

```
 ____   ___  ____  _   _
|  _ \ / _ \|  _ \| \ | |
| | | | | | | |_) |  \| |
| |_| | |_| |  _ <| |\  |
|____/ \___/|_| \_\_| \_|
```

Dorn is a simple command-line task manager that helps you track todos, deadlines, and events. Tasks are automatically saved between sessions.

---

## Quick Start

1. Ensure you have **Java 17**  installed.
2. Download the latest `dorn.jar` from the releases page.
3. Run the application:
   ```
   java -jar dorn.jar
   ```
4. Type a command and press Enter to get started.

---

## Features

- Add three types of tasks: **Todos**, **Deadlines**, and **Events**
- Mark tasks as done or not done
- Delete tasks
- Search tasks by keyword
- Tasks are **automatically saved** to `Data/tasks.json` on exit and loaded on startup

---

## Commands

### Add a Todo
Adds a task with no associated date.

**Format:** `todo DESCRIPTION`

**Example:**
```
todo buy groceries
```
```
____________________________________________________________
Got it. I've added this task:
   [T][ ] buy groceries
Now you have 1 tasks in the list
____________________________________________________________
```

---

### Add a Deadline
Adds a task with a due date.

**Format:** `deadline DESCRIPTION /by YYYY-MM-DD`

**Example:**
```
deadline submit assignment /by 2025-03-15
```
```
____________________________________________________________
Got it. I've added this task:
   [D][ ] submit assignment (by: Mar 15 2025)
Now you have 2 tasks in the list
____________________________________________________________
```

---

### Add an Event
Adds a task with a start and end date.

**Format:** `event DESCRIPTION /from YYYY-MM-DD /to YYYY-MM-DD`

**Example:**
```
event team retreat /from 2025-04-01 /to 2025-04-03
```
```
____________________________________________________________
Got it. I've added this task:
   [E][ ] team retreat (from: Apr 1 2025 to: Apr 3 2025)
Now you have 3 tasks in the list
____________________________________________________________
```

---

### List All Tasks
Displays all tasks currently in your list.

**Format:** `list`

**Example:**
```
list
```
```
____________________________________________________________
 Here are the tasks in your list:
 1.[T][ ] buy groceries
 2.[D][ ] submit assignment (by: Mar 15 2025)
 3.[E][ ] team retreat (from: Apr 1 2025 to: Apr 3 2025)
____________________________________________________________
```

---

### Mark a Task as Done
Marks the specified task as completed.

**Format:** `mark TASK_NUMBER`

**Example:**
```
mark 1
```
```
____________________________________________________________
 Nice! I've marked this task as done:
   [T][X] buy groceries
____________________________________________________________
```

---

### Mark a Task as Not Done
Reverts a completed task back to incomplete.

**Format:** `unmark TASK_NUMBER`

**Example:**
```
unmark 1
```
```
____________________________________________________________
 OK, I've marked this task as not done yet:
   [T][ ] buy groceries
____________________________________________________________
```

---

### Find Tasks by Keyword
Searches for tasks whose descriptions contain the given keyword (case-insensitive).

**Format:** `find KEYWORD`

**Example:**
```
find assignment
```
```
____________________________________________________________
Here are the matching tasks in your list:
	1.[D][ ] submit assignment (by: Mar 15 2025)
____________________________________________________________
```

---

### Delete a Task
Removes the specified task from the list permanently.

**Format:** `delete TASK_NUMBER`

**Example:**
```
delete 2
```
```
____________________________________________________________
Noted. I've removed this task:
   [D][ ] submit assignment (by: Mar 15 2025)
Now you have 2 tasks in the list.
____________________________________________________________
```

---

### Exit
Saves all tasks and exits the application.

**Format:** `bye`

```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

---

## Task Display Format

| Symbol | Meaning          |
|--------|------------------|
| `[T]`  | Todo             |
| `[D]`  | Deadline         |
| `[E]`  | Event            |
| `[X]`  | Task is done     |
| `[ ]`  | Task is not done |

---

## Data Storage

Tasks are automatically saved to `Data/tasks.json` whenever you run the `bye` command. The file is loaded automatically on the next startup — no manual saving required.

---

## Command Summary

| Command | Format |
|---------|--------|
| Add todo | `todo DESCRIPTION` |
| Add deadline | `deadline DESCRIPTION /by YYYY-MM-DD` |
| Add event | `event DESCRIPTION /from YYYY-MM-DD /to YYYY-MM-DD` |
| List tasks | `list` |
| Mark done | `mark TASK_NUMBER` |
| Mark not done | `unmark TASK_NUMBER` |
| Find tasks | `find KEYWORD` |
| Delete task | `delete TASK_NUMBER` |
| Exit | `bye` |