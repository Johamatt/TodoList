import React, { useState } from 'react';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';

export default function Edittask(props) {
  const [open, setOpen] = useState(false);
  const [task, setTask] = useState({ id: '', description: '', date: '', checked: '' });

  const handleClickOpen = () => {
    setTask({ id: props.task.id, description: props.task.description, date: props.task.date, checked: props.task.checked });
    setOpen(true);
  }

  const handleClose = () => {
    console.log(props.task.id)
    props.updateTask('http://localhost:8080/api/tasks?id=' + props.task.id, task)
    setOpen(false);
  }

  const handleCancel = () => {
    setOpen(false);
  }

  const inputChanged = (event) => {
    setTask({ ...task, [event.target.name]: event.target.value });
  }

  return (
    <div>
      <Button size="small" color="primary" onClick={handleClickOpen}>
        Edit
      </Button>
      <Dialog open={open} disableBackdropClick={true} disableEscapeKeyDown={true} onClose={handleClose} aria-labelledby="form-dialog-title">
        <DialogTitle id="form-dialog-title">Edit Content</DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            margin="dense"
            id="description"
            name="description"
            value={task.description}
            onChange={inputChanged}
            label="Description"
            fullWidth
          />
          <TextField
            margin="dense"
            type="date"
            id="date"
            name="date"
            value={task.date}
            onChange={inputChanged}
            label="Date"
            fullWidth
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleCancel} color="primary">
            Cancel
          </Button>
          <Button onClick={handleClose} color="primary">
            Save
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  )
}