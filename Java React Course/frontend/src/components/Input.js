import React from "react";

const Input = (props) =>{

    let inputClassName = 'form-control'
    inputClassName += props.hasError !== undefined ? (props.hasError ? ' is-invalid' : ' is-valid') : '';
    return(
    <div>
        {props.label && <label>{props.label}</label>}
        <input type={props.type || 'text'} 
        className = {inputClassName}
        placeholder={props.placeholder}
        value={props.value}
        onChange = {props.onChange}/>
        {props.hasError && <span className="invalid-feedback">
            {props.error}
        </span>}
    </div>
   )
};

Input.defaultProps = {
    onChange: () =>{}
};

export default Input;