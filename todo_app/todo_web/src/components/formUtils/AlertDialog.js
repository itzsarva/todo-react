import React from 'react';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';

export default function AlertDialog({bodyText, headerText, openDialog, handleClose, onBackdropClick, buttonText}) {
  return (
    <div>
      <Dialog onBackdropClick={onBackdropClick}
        open={openDialog}
        onClose={handleClose}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle style={{background: 'greenyellow', color: 'black'}} id="alert-dialog-title">{headerText}</DialogTitle>
        <DialogContent style={{background: 'whitesmoke', color: 'black'}}>
          <DialogContentText id="alert-dialog-description" style={{background: '#343a40', color: 'white'}}>
                {bodyText}
          </DialogContentText>
        </DialogContent>
        <DialogActions style={{background: 'greenyellow'}}>
          <Button onClick={handleClose} color="primary">
            {buttonText}
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}
