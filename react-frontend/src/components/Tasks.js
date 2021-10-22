import React, { Component } from 'react'
import TaskDataService from "../services/task.service";
import update from 'immutability-helper';



class TasksContainer extends Component {
    constructor(props) {
        super(props)
        this.state = {
            tasks: [],
            inputValue: ''
        }

    }

    getTasks() {
        TaskDataService.getAll()
            .then(response => {
                this.setState({
                    tasks: response.data
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    componentDidMount() {
        this.getTasks()
    }

    createTask = (e) => {
        if (e.key === 'Enter') {
            var data = {
                task: this.state.inputValue
            };
            if (data.task !== '') {
                TaskDataService.create(data)
                    .then(response => {
                        console.log(response);
                        this.setState({ inputValue: "" });
                        this.getTasks();
                    })

                    .catch(e => {
                        console.log(e);
                    });
            }

        }
    }

    handleChange = (e) => {
        this.setState({ inputValue: e.target.value });
    }

    updateTask = (e, id) => {
        TaskDataService.update(id, { completed: e.target.checked })
            .then(response => {
                const taskIndex = this.state.tasks.findIndex(x => x.id === response.data.id)
                const tasks = update(this.state.tasks, {
                    [taskIndex]: { $set: response.data }
                })
                this.setState({
                    tasks: tasks
                })
            })
            .catch(error => console.log(error))
    }

    deleteTask = (id) => {
        TaskDataService.deleteTask(id)
            .then(response => {
                console.log(response.data);
                this.getTasks();
            })
            .catch(e => {
                console.log(e);
            });
    }

    render() {
        return (
            <div>
                <div className="inputContainer">
                    <input className="taskInput" type="text"
                        placeholder="+ Add a task and press enter" maxLength="50"
                        onKeyPress={this.createTask}
                        value={this.state.inputValue} onChange={this.handleChange} />
                </div>
                <div className="listWrapper">
                    <ul className="taskList">
                        {this.state.tasks?.map((task) => {
                            return (
                                <li className="task" key={task.id}>
                                    <input className="taskCheckbox" type="checkbox"
                                        checked={task.completed}
                                        onChange={(e) => this.updateTask(e, task.id)} />
                                    <label className="taskLabel">{task.task}</label>
                                    <span className="deleteTaskBtn"
                                        onClick={(e) => this.deleteTask(task.id)}>
                                        X
                                    </span>
                                </li>
                            )
                        })}
                    </ul>
                </div>
            </div>
        )
    }
}

export default TasksContainer