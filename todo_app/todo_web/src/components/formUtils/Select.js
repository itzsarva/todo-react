import React from 'react';
import './formUtils.css';


const Select = (props) => {
	return(<div className="form-group">
			<label htmlFor={props.name} className="carLabel"> {props.title} </label>
		    <select
		      id = {props.name}
		      name={props.name}
		      value={props.value}
		      onChange={props.onChange}
		      className="form-control carSelect">
		      <option value="" disabled className="carSelect">{props.placeholder}</option>
		      {props.options.map(option => {
		        return (
		          <option className="carSelect"
		            key={`${option.label} ${option.value}`}
		            value={option.value}
		            label={option.label}>{option.label}</option>
		        );
		      })}
		    </select>
  </div>)
}

export default Select;