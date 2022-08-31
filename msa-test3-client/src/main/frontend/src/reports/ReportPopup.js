import React, {useEffect, useState} from 'react';
import axios from 'axios';

import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';

function preventDefault(event) {
  event.preventDefault();
}

class ReportPopup extends React.Component {

//    const [open, setOpen] = React.useState(false);

//    const [reports, setReports] = useState([])
  constructor(props) {
    super(props);

    this.state = {
       report: {
            id: '',
            tstcd: '',
            count: '0',
            date: new Date()
            },
       open : false,
       action : '',
       dataChanged: false
    };

//    this.handleSave = this.handleSave.bind(this);

//    this.state = {open: false};
  }

 handleClickReg = (e) => {
    this.setState({
      open: true,
      action: 'reg'
    });
  };

   handleClickDel = (e) => {
      this.setState({
        open: true,
        action: 'del'
      });
    };

  chageData = (e) => {
     this.setState({
       dataChanged: true
     });
   };

 handleClose = (e) => {
    this.setState({
      open: false
    });
  };

 handleSave = (e) => {
    console.log(this.state.report);

    if (this.state.action == 'reg') {
        console.log (this.state.action);
        axios.post("/api/reports", this.state.report )
            .then(
                    (response) => {
                 console.log(response);
                 return this.chageData();
            }).catch(function (error) {
                // 오류발생시 실행
            }).then(function() {
                // 항상 실행
            });
    }else if(this.state.action == 'del') {
        console.log (this.state.action);

         axios.delete("/api/reports/" + this.state.report.id , this.state.report )
             .then(
                     (response) => {
                  console.log(response);
                  return this.chageData();
             }).catch(function (error) {
                 // 오류발생시 실행
             }).then(function() {
                 // 항상 실행
             });
    }

//    this.chageData();

    this.handleClose();
  };

   handleIdChange = (event) => {
          this.setState({
            report: {
              id: event.target.value
            }
          });
        };

 handleTstcdChange = (event) => {
        this.setState({
          report: {
            tstcd: event.target.value,
            count: this.state.report.count
          }
        });
      };

 handleCountChange = (event) => {
        this.setState({
          report : {
            tstcd: this.state.report.tstcd,
            count: event.target.value
          }
        });
      };

   render() {

    const parentPassedHandler = this.props.parentPassedHandler(this.state.dataChanged);

     return (
    <div>
      <Button variant="outlined" onClick={this.handleClickReg}>
        검사등록
      </Button>
      <Button variant="outlined" onClick={this.handleClickDel}>
          검사삭제
        </Button>
      <Dialog open={this.state.open} onClose={this.handleClose}>
        <DialogTitle>Subscribe</DialogTitle>
        <DialogContent>
          <DialogContentText>
            To subscribe to this website, please enter your email address here. We
            will send updates occasionally.
          </DialogContentText>
          <TextField
          autoFocus
          margin="dense"
          id="id"
          label="id"
          type="text"
          fullWidth
          variant="standard"
          value={this.state.report.id}
          onChange={this.handleIdChange}
        />
          <TextField
            autoFocus
            margin="dense"
            id="tstcd"
            label="test cd"
            type="text"
            fullWidth
            variant="standard"
            value={this.state.report.tstcd}
            onChange={this.handleTstcdChange}
          />
          <TextField
              autoFocus
              margin="dense"
              id="count"
              label="count"
              type="text"
              fullWidth
              variant="standard"
              value={this.state.report.count}
              onChange={this.handleCountChange}
            />
        </DialogContent>
        <DialogActions>
          <Button onClick={this.handleClose}>Cancel</Button>
          <Button onClick={this.handleSave}>Subscribe</Button>
        </DialogActions>
      </Dialog>
    </div>
  )
  };
}

export default ReportPopup;