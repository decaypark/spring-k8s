//import * as React from 'react';
import React, {useEffect, useState} from 'react';
import axios from 'axios';

import Link from '@mui/material/Link';
import Typography from '@mui/material/Typography';
import Title from './Title';

import BasicModal from '../popup/BasicModal';


function preventDefault(event) {
//  event.preventDefault();
    alert("1");
}

export default function Deposits() {

   const [member, setMember] = useState('')

    useEffect(() => {
        axios.get('/api/hello')
        .then(response => setMember(response.data))
        .catch(error => console.log(error))
    }, []);

  return (
    <React.Fragment>
      <Title>오늘 의뢰 건수</Title>
      <Typography component="p" variant="h4">
        {member.id}
      </Typography>
      <Typography color="text.secondary" sx={{ flex: 1 }}>
        {member.name}
      </Typography>
      <div>
        <Link color="primary" href="#" onClick={preventDefault}>
          View balance
        </Link>
      </div>
      <div>
        <BasicModal title='제목...' content='sample 내용입니다.'/>
      </div>
    </React.Fragment>
  );
}
