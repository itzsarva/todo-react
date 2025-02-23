import React from 'react';
import './formUtils.css';


const TextArea = (props) => (  
  <div className="form-group">
    <label className="form-label">{props.title}</label>
    <textarea
      className="form-control"
      name={props.name}
      rows={props.rows}
      cols = {props.cols}
      value={props.value}
      onChange={props.onChange}
      placeholder={props.placeholder} />
  </div>
);

export default TextArea;