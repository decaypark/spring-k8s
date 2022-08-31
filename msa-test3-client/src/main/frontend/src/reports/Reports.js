import * as React from 'react';

import ReportGrid from './ReportGrid';
import ReportPopup from './ReportPopup';

class Reports extends React.Component {
  constructor(props) {
    super(props);
    this.state = {date: new Date(),
        refresh: false
    };
  }

  componentWillRiceiveProps() {
    console.log ('...componentWillRiceiveProps');

   this.setState({
     refresh: true
   });
  }

  componentDidMount() {
//    this.timerID = setInterval(
//      () => this.tick(),
//      1000
//    );
  }

  componentWillUnmount() {
    clearInterval(this.timerID);
  }

  tick() {
    this.setState({
      date: new Date()
    });
  }

  handleSave  = (e) => {

       console.log('..handleSave');
       console.log('e='+e);

       if (e != null && e != "" && e.dataChanged == true ) {

           this.setState({
             refresh: true
           });

       }
     };

  render() {

    const parentPassedHandler = this.handleSave.bind(this);

    return (
      <div>
        <h2>{this.state.date.toLocaleTimeString()}</h2>
        <ReportGrid childHandler={this.state.refresh} />
        <ReportPopup parentPassedHandler={parentPassedHandler} />
      </div>
    );
  }
}

export default Reports;