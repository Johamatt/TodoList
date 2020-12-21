

import React, { useState, useEffect } from 'react';
import 'react-table-v6/react-table.css';
import Snackbar from '@material-ui/core/Snackbar';
import moment from 'moment';
import { Table, Button} from 'antd';
import 'antd/dist/antd.css';
import {CheckOutlined, CloseCircleTwoTone, CheckCircleTwoTone, QuestionOutlined} from '@ant-design/icons';
import EditTask from './EditTask';
import Addtask from './Addtask';

export default function Task() {
  const [undoneTask, setUndoneTask] = useState([]);
  const [doneTask, setDoneTask] = useState([]);
  const [tasks, setTasks] = useState([]);
  const [open, setOpen] = useState(false);
  const [msg, setMsg] = useState('');

  useEffect(() => {
    getTasks();
  }, [])

  const getTasks = () => {
    let undone = [];
    let done = [];
    fetch('http://localhost:8080/api/tasks')
      .then(response => response.json())
      .then(data => setTasks(data))
      .catch(err => console.error(err))

    tasks.forEach(e => {
      if (e.checked == 1) {
        undone.push(e)
      } else {
        done.push(e)
      }
    }
    )
    setUndoneTask(undone);
    setDoneTask(done);
  }

  const deleteTask = (task) => {
    if (window.confirm('Are you sure?')) {
      fetch(task,
        {
          method: 'DELETE',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(task)
        }
      )
        .then(_ => getTasks())
        .then(_ => {
          setMsg('Task deleted');
          setOpen(true);
        })
        .catch(err => console.error(err))
    }
  }

  const addTask = (task) => {
    fetch('http://localhost:8080/api/tasks',
      {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(task)
      }
    )
      .then(_ => getTasks())
      .then(_ => {
        setMsg('New task added');
        setOpen(true);
      })
      .catch(err => console.error(err))
  }

  const updateTask = (link, task) => {
    fetch(link, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(task)
    }
    )
      .then(_ => getTasks())
      .then(_ => {
        setMsg('Task updated');
        setOpen(true);
      })
      .catch(err => console.error(err));
  }

  const handleClose = () => {
    setOpen(false);
  }

  const handleCheck = (task, link) => {

    if (task.checked == 1) {

      task.checked = '0'
    } else {

      task.checked = '1'
    }
    updateTask(link, task)
  }

  class FilterTable extends React.Component {
    render() {
      const columns = [
        {
          width: '8%',
          title: 'status',
          render: (row) => {

            if (row.checked == 1) {
              return (<div style={{ align: 'center' }}>
                <Button style={{ margin: 'auto' }}
                  icon={<CheckCircleTwoTone twoToneColor="#52c41a" style={{}} />}
                  onClick={() => handleCheck(row, "http://localhost:8080/api/tasks?id=" + row.id)}>
                </Button>
              </div>
              )
            } else {
              return (<div style={{ align: 'center' }}>
                <Button style={{ margin: 'auto' }}
                  icon={<CloseCircleTwoTone twoToneColor="#bfaa06" style={{}} />}
                  onClick={() => handleCheck(row, "http://localhost:8080/api/tasks?id=" + row.id)}>
                </Button>
              </div>
              )
            }
          },
          sorter: (a, b) => a.checked - b.checked,
        },
        {
          title: 'Task',
          dataIndex: 'description',
          key: 'description',
          width: '60%',
        },
        {
          title: 'Date',
          dataIndex: 'date',
          key: 'date',
          width: '30%',
          render: (date) =>
            <div>{moment(date).format('ll')}</div>,
          sorter: (a, b) => new Date(a.date) - new Date(b.date),
        },
        {
          title: 'Operations',
          colSpan: 3,
          render: (row) => (<EditTask task={row} updateTask={updateTask} />)
        },
        {
          render: (row) => (<Button type="primary" danger shape='round' size='small' onClick={() => deleteTask("http://localhost:8080/api/tasks?id=" + row.id)}>Delete</Button>)
        }
      ];
      return <Table columns={columns} dataSource={tasks} bordered />;
    }
  }
  return (
    <div>
      <div style={{}}>
        <Addtask addTask={addTask} />
        <FilterTable></FilterTable>
        <Snackbar open={open} autoHideDuration={3000}
          onClose={handleClose} message={msg} />
      </div>
    </div>
  )
}







