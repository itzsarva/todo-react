import React from 'react';
import './formUtils.css';


const Button = (props) => {
	return(
	<button 
		style= {props.style} 
		className = {props.type=='primary'? 'btn btn-primary' : 'btn btn-secondary'}
		onClick= {props.action} > 
		{props.title} 
	</button>)
}


export default Button;