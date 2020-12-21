import React, { useState } from 'react';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import { PlusSquareFilled } from '@ant-design/icons';

export default function AddTask(props) {
    const [open, setOpen] = useState(false);
    const [task, setTask] = useState({ description: '', date: '', checked: '1' });

    const handleClickOpen = () => {
        setOpen(true);
    }

    const handleClose = () => {
        props.addTask(task);
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
            <Button style={{ margin: 10 }} variant="outlined" color="primary" onClick={handleClickOpen}>
                <PlusSquareFilled />&nbsp;Add task
            </Button>
            <Dialog open={open} disableBackdropClick={true} disableEscapeKeyDown={true} onClose={handleClose} aria-labelledby="form-dialog-title">
                <DialogTitle id="form-dialog-title">New task</DialogTitle>
                <DialogContent>
                    <TextField
                        autoFocus
                        margin="dense"
                        id="description"
                        name="description"
                        value={task.description}
                        onChange={inputChanged}
                        label="Todo"
                        fullWidth
                    />
                    <TextField
                        margin="dense"
                        id="date"
                        type="date"
                        name="date"
                        value={task.date}
                        onChange={inputChanged}
                        fullWidth
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleCancel} color="primary">Cancel</Button>
                    <Button onClick={handleClose} color="primary">Save</Button>
                </DialogActions>
            </Dialog>
        </div>
    )
}