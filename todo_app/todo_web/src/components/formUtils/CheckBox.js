import React from 'react';
import './formUtils.css';


const CheckBox = (props) => {
	return( <div className="form-group">
    <label htmlFor={props.name} className="form-label">{props.title}</label>
    <div className="checkbox">
      {props.options.map(option => {
        return (
          <label key={option} className="checkbox-inline">
            <input
              id = {props.name}
              name={props.name}
              onChange={props.onChange}
              value={option}
              checked={ props.selectedOptions.indexOf(option) > -1 }
              type="checkbox" /> {option}
          </label>
        );
      })}
    </div>
  </div>
);

}

export default CheckBox;