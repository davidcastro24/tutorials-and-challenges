import React from "react";
import { render, fireEvent} from "@testing-library/react";
import Input from "./Input";

describe('Layout', () =>{
   it('has input item', ()=>{
    const {container} = render(<Input/>);  
    const input = container.querySelector('input');
    expect(input).toBeInTheDocument();
   });
   it('displays label provided in props', () =>{
    const {queryByText} = render(<Input label="Test label"/>);
    const label = queryByText('Test label');
    expect(label).toBeInTheDocument();
   });
   it('displays label only if provided in props', () =>{
    const {container} = render(<Input/>);
    const label = container.querySelector('label');
    expect(label).not.toBeInTheDocument();
   });
   it('text type for input when not provided as prop', () =>{
    const {container} = render(<Input/>);
    const input = container.querySelector('input');
    expect(input.type).toBe('text');
   });
   it('password type for input when provided as prop', () =>{
    const {container} = render(<Input type="password"/>);
    const input = container.querySelector('input');
    expect(input.type).toBe('password');
   });
   it('display placeholder when provided as prop', () =>{
    const {container} = render(<Input placeholder="test"/>);
    const input = container.querySelector('input');
    expect(input.placeholder).toBe('test');
   });
   it('display value when provided as prop', () =>{
    const {container} = render(<Input value="test"/>);
    const input = container.querySelector('input');
    expect(input.value).toBe('test');
   });
   it('has onChange callback when provided as prop', () =>{
    const onChange = jest.fn();
    const {container} = render(<Input onChange = {onChange}/>);
    const input = container.querySelector('input');
    fireEvent.change(input, {target: {value: 'new-input'}});
    expect(onChange).toHaveBeenCalledTimes(1);
   });
   it('has default style when no error or success', () =>{
    const {container} = render(<Input/>);
    const input = container.querySelector('input');
    expect(input.className).toBe('form-control');
   });
   it('has success style when hasError property is false', () =>{
    const {container} = render(<Input hasError ={false}/>);
    const input = container.querySelector('input');
    expect(input.className).toBe('form-control is-valid');
   });
   it('has error style when hasError property is true', () =>{
    const {container} = render(<Input hasError ={true}/>);
    const input = container.querySelector('input');
    expect(input.className).toBe('form-control is-invalid');
   });
   it('displays error text if provided', () =>{
    const {queryByText} = render(<Input hasError ={true} error="must not be null"/>);
    expect(queryByText('must not be null')).toBeInTheDocument();
   });
   it(' does not displays error text if not provided', () =>{
    const {queryByText} = render(<Input error="must not be null"/>);
    expect(queryByText('must not be null')).not.toBeInTheDocument();
   });
});
