# Dorn — Task Manager Chatbot

```
 ____   ___  ____  _   _
|  _ \ / _ \|  _ \| \ | |
| | | | | | | |_) |  \| |
| |_| | |_| |  _ <| |\  |
|____/ \___/|_| \_\_| \_|
```

Dorn is a command-line task manager that helps you track todos, deadlines, and events.
Tasks are automatically saved between sessions so nothing is ever lost.

---

## Quick Start

1. Ensure you have **Java 17** or later installed.
2. Download the latest `ip.jar` from the releases page.
3. Run the application:
   ```
   java -jar ip.jar
   ```
4. A help guide is shown automatically on startup. Type any command and press Enter.

---

## Features

- Add three types of tasks: **Todos**, **Deadlines**, and **Events**
- Mark tasks as done or revert them to not done
- Delete tasks by number
- Search tasks by keyword or multi-word phrase (case-insensitive)
- Tasks are **automatically saved** to `Data/tasks.json` on exit and reloaded on the next startup
- Built-in `help` command shows all commands at any time

---

## Commands

### `todo` — Add a To-Do

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
	Now you have 1 tasks in the list.
	____________________________________________________________
```

---

### `deadline` — Add a Deadline

Adds a task with a specific due date.

**Format:** `deadline DESCRIPTION /by YYYY-MM-DD`

**Example:**
```
deadline submit assignment /by 2026-04-15
```
```
	____________________________________________________________
	Got it. I've added this task:
	  [D][ ] submit assignment (by: Apr 15 2026)
	Now you have 2 tasks in the list.
	____________________________________________________________
```

---

### `event` — Add an Event

Adds a task that spans a date range. The start date must not be after the end date.

**Format:** `event DESCRIPTION /from YYYY-MM-DD /to YYYY-MM-DD`

**Example:**
```
event team retreat /from 2026-05-01 /to 2026-05-03
```
```
	____________________________________________________________
	Got it. I've added this task:
	  [E][ ] team retreat (from: May 1 2026 to: May 3 2026)
	Now you have 3 tasks in the list.
	____________________________________________________________
```

---

### `list` — List All Tasks

Displays all tasks currently in your list, numbered from 1.
Shows a message if the list is empty.

**Format:** `list`

**Example:**
```
list
```
```
	____________________________________________________________
	Here are the tasks in your list:
	  1. [T][ ] buy groceries
	  2. [D][ ] submit assignment (by: Apr 15 2026)
	  3. [E][ ] team retreat (from: May 1 2026 to: May 3 2026)
	____________________________________________________________
```

---

### `mark` — Mark a Task as Done

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

### `unmark` — Mark a Task as Not Done

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

### `delete` — Delete a Task

Permanently removes the specified task from the list.
All tasks after it are renumbered automatically.

**Format:** `delete TASK_NUMBER`

**Example:**
```
delete 2
```
```
	____________________________________________________________
	Noted. I've removed this task:
	  [D][ ] submit assignment (by: Apr 15 2026)
	Now you have 2 tasks in the list.
	____________________________________________________________
```

---

### `find` — Search Tasks

Searches all task descriptions for a keyword or phrase (case-insensitive).
Multi-word queries are supported — all tokens after `find` are treated as a single phrase.

**Format:** `find KEYWORD(S)`

**Examples:**
```
find assignment
find buy a car
```
```
	____________________________________________________________
	Here are the matching tasks in your list:
	  1. [T][ ] buy a car
	____________________________________________________________
```

---

### `help` — Show Command Guide

Prints a full list of all commands and their usage directly in the terminal.
This is also displayed automatically when Dorn starts up.

**Format:** `help`

---

### `bye` — Save and Exit

Saves all tasks to disk and exits the application.

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

## Error Handling

Dorn validates all input before creating tasks. Common errors and their messages:

| Situation | Error Message |
|-----------|---------------|
| Missing description | `The description of a todo/deadline/event cannot be empty.` |
| Missing `/by` on deadline | `Please specify the deadline with /by` |
| Missing `/from` or `/to` on event | `Please specify event duration with /from and /to.` |
| Invalid date format | `Invalid date format for /by. Please use YYYY-MM-DD (e.g., 2026-03-24).` |
| Event start date after end date | `Event start date (YYYY-MM-DD) cannot be after end date (YYYY-MM-DD).` |
| Task number out of range | `Task number N does not exist.` |
| Non-integer task number | `Please provide a valid task number.` |
| Unknown command | `I'm sorry, but I don't know what that means :-(` |

---

## Data Storage

Tasks are saved to `Data/tasks.json` whenever you run `bye`. The file is loaded
automatically on the next startup — no manual saving required. If the file does not
exist yet, Dorn starts with an empty task list.

---

## Command Summary

| Command | Format |
|---------|--------|
| Add todo | `todo DESCRIPTION` |
| Add deadline | `deadline DESCRIPTION /by YYYY-MM-DD` |
| Add event | `event DESCRIPTION /from YYYY-MM-DD /to YYYY-MM-DD` |
| List all tasks | `list` |
| Mark done | `mark TASK_NUMBER` |
| Mark not done | `unmark TASK_NUMBER` |
| Delete task | `delete TASK_NUMBER` |
| Find tasks | `find KEYWORD(S)` |
| Show help | `help` |
| Save and exit | `bye` |