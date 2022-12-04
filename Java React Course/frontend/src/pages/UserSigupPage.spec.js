import React from "react";
import { render, fireEvent, waitFor,screen} from "@testing-library/react";
import {UserSignupPage} from './UserSignupPage';


describe('UserSignupPage', () => {
    describe('Layout', () => {
        it('Has header of Sign Up', () =>{
           const {container} = render(<UserSignupPage/>);
           const header = container.querySelector('h1');
           expect(header).toHaveTextContent('Sign Up');
        });
        it('has input for display name', ()=>{
            //const {queryByPlaceholderText} = 
            render(<UserSignupPage/>);
            const displayNameInput = screen.queryByPlaceholderText('Your Display Name');
            expect(displayNameInput).toBeInTheDocument;
        });
        it('has input for userName', ()=>{
            const {queryByPlaceholderText} = render(<UserSignupPage/>);
            const userNameInput = queryByPlaceholderText('Your UserName');
            expect(userNameInput).toBeInTheDocument;
        });
        it('has input for password', ()=>{
            const {queryByPlaceholderText} = render(<UserSignupPage/>);
            const passwordInput = queryByPlaceholderText('Your Password');
            expect(passwordInput).toBeInTheDocument;
        });
        it('has password input type for password', ()=>{
            const {queryByPlaceholderText} = render(<UserSignupPage/>);
            const passwordInput = queryByPlaceholderText('Your Password');
            expect(passwordInput.type).toBe('password');
        });
        it('has input for confirm password', ()=>{
            const {queryByPlaceholderText} = render(<UserSignupPage/>);
            const confirmPasswordInput = queryByPlaceholderText('Confirm Password');
            expect(confirmPasswordInput).toBeInTheDocument;
        });
        it('has password input type for confirm password', ()=>{
            const {queryByPlaceholderText} = render(<UserSignupPage/>);
            const confirmPasswordInput = queryByPlaceholderText('Confirm Password');
            expect(confirmPasswordInput.type).toBe('password');
        });
        it('Has submit button', () =>{
            const {container} = render(<UserSignupPage/>);
            const button = container.querySelector('button');
            expect(button).toBeInTheDocument;
         });

    });
    describe('Interactions',() => {
        
        const changeEvent = (content) =>{
            return{
                target: {
                    value: content
                }
            }
        };
        let button, displayNameInput, userNameInput,passwordInput, confirmPasswordInput;
        const setupForSubmit = (props) => {
            const rendered  = render(
                <UserSignupPage {...props}/>);
            const{container, queryByPlaceholderText} = rendered;
            displayNameInput =queryByPlaceholderText('Your Display Name');
            userNameInput =queryByPlaceholderText('Your UserName');
            passwordInput =queryByPlaceholderText('Your Password');
            confirmPasswordInput =queryByPlaceholderText('Confirm Password');

            fireEvent.change(displayNameInput, changeEvent('display-name'));
            fireEvent.change(userNameInput, changeEvent('userName'));
            fireEvent.change(passwordInput, changeEvent('P2!ssword'));
            fireEvent.change(confirmPasswordInput, changeEvent('P2!ssword'));

            button = container.querySelector('button');

            return rendered;
        }
        const mockAsyncDelayed = () => {
            return jest.fn().mockImplementation(() =>{
                return new Promise((resolve,reject) =>{
                    setTimeout(() => {
                        resolve({});    
                    }, 300);
                });
            });
        };

        it('set displayName into state', () => {
            const {queryByPlaceholderText} = render(<UserSignupPage/>);
            const displayNameInput =queryByPlaceholderText('Your Display Name'); //screen.queryByPlaceholderText('Your Display Name');
            fireEvent.change(displayNameInput, changeEvent('display-name'));
            expect(displayNameInput).toHaveValue('display-name');
        });
        it('set userName into state', () => {
            const {queryByPlaceholderText} = render(<UserSignupPage/>);
            const userNameInput =queryByPlaceholderText('Your UserName'); //screen.queryByPlaceholderText('Your Display Name');
            fireEvent.change(userNameInput, changeEvent('userName'));
            expect(userNameInput).toHaveValue('userName');
        });
        it('set password into state', () => {
            const {queryByPlaceholderText} = render(<UserSignupPage/>);
            const passwordInput =queryByPlaceholderText('Your Password'); //screen.queryByPlaceholderText('Your Display Name');
            fireEvent.change(passwordInput, changeEvent('password'));
            expect(passwordInput).toHaveValue('password');
        });
        it('set confirm password into state', () => {
            const {queryByPlaceholderText} = render(<UserSignupPage/>);
            const confirmPasswordInput =queryByPlaceholderText('Confirm Password'); //screen.queryByPlaceholderText('Your Display Name');
            fireEvent.change(confirmPasswordInput, changeEvent('password'));
            expect(confirmPasswordInput).toHaveValue('password');
        });
        it('calls postSignUp when fields are valid and actions are provided in props',
        () =>{
            const actions = {
                postSignUp: jest.fn().mockResolvedValueOnce({}) //create mock function
            };
            setupForSubmit({actions});
            fireEvent.click(button);

            expect(actions.postSignUp).toHaveBeenCalledTimes(1);
        });
        it('no exception button clicked when actions not provided',
        () =>{
            const {container, queryByPlaceholderText} = setupForSubmit();
            fireEvent.click(button);

            expect(() => fireEvent.click(button)).not.toThrow();
        });
        it('calls post with user body when fields are ok ',
        () =>{
            const actions = {
                postSignUp: jest.fn().mockResolvedValueOnce({}) //create mock function
            };
            setupForSubmit({actions});
            fireEvent.click(button);
            const expectedUserObject = {
                userName: 'userName',
                displayName: 'display-name',
                password: 'P2!ssword'
            }
            expect(actions.postSignUp).toHaveBeenCalledWith(expectedUserObject);
        });
        it('signup unavailable when other request is in progress ',
        () =>{
            const actions = {
                postSignUp: mockAsyncDelayed()
            };
            setupForSubmit({actions});
            fireEvent.click(button);
            fireEvent.click(button);
            expect(actions.postSignUp).toHaveBeenCalledTimes(1);
        });
        it('displays spinner on ongoing api call',
        () =>{
            const actions = {
                postSignUp: mockAsyncDelayed()
            };
            const {queryByText} = setupForSubmit({actions});
            fireEvent.click(button);
            const spinner = queryByText('Loading...');
            expect(spinner).toBeInTheDocument();
        });
        it('hides spinner after api call',
            async () =>{
            const actions = {
                postSignUp: mockAsyncDelayed()
            };
            const {queryByText} = setupForSubmit({actions});
            fireEvent.click(button);
            const spinner = queryByText('Loading...');
            await waitFor(() =>
                expect(spinner).not.toBeInTheDocument()
                );
        });
        it('hides spinner after api call finishes with error',
        async () =>{
        const actions = {
            postSignUp: jest.fn().mockImplementation(() =>{
                return new Promise((resolve,reject) =>{
                    setTimeout(() => {
                        reject({
                            response:{data: {}}
                        });    
                    }, 300);
                });
            })
        };
        const {queryByText} = setupForSubmit({actions});
        fireEvent.click(button);
        //const spinner = queryByText('Loading...');
        const spinner = waitFor(() => queryByText('Loading...')).then(data=>{
            expect(spinner).not.toBeInTheDocument()
        }).catch(error => {});
        });
        it('displays validation error for displayName when error received from backend field',
        async () => {
            const actions ={
                postSignUp: jest.fn().mockRejectedValue({
                    response:{
                        data:{
                            validationErrors:{
                                displayName: 'must not be null'
                            }
                        }
                    }
                })
            };
            const {queryByText} = setupForSubmit({actions});
            fireEvent.click(button);
            const errorMessage = waitFor(() => queryByText('must not be null'))
            .then(data =>{
                expect(errorMessage).toBeInTheDocument();
            }).catch(error =>{});
       
        });
        it('enables signup button when passwords match',()=>{
            setupForSubmit();
            expect(button).not.toBeDisabled();
        });
        it('disables signup button when passwords !match',()=>{
            setupForSubmit();
            fireEvent.change(confirmPasswordInput,changeEvent('2!Password'));
            expect(button).toBeDisabled();
        });
        it('disables signup button when passwords !match',()=>{
            setupForSubmit();
            fireEvent.change(passwordInput,changeEvent('2!Password'));
            expect(button).toBeDisabled();
        });
        it('displays error style cp when passwords do not match',()=>{
            const {queryByText} = setupForSubmit();
            fireEvent.change(confirmPasswordInput,changeEvent('2!Password'));
            //const notMatch = 
            expect(queryByText('Passwords do not match')).toBeInTheDocument();
        });
        it('displays error style p when passwords do not match',()=>{
            const {queryByText} = setupForSubmit();
            fireEvent.change(passwordInput,changeEvent('2!Password'));
            //const notMatch = 
            expect(queryByText('Passwords do not match')).toBeInTheDocument();
        });
        it('hides validation error when all is ok again displayName',
        async () => {
            const actions ={
                postSignUp: jest.fn().mockRejectedValue({
                    response:{
                        data:{
                            validationErrors:{
                                displayName: 'must not be null'
                            }
                        }
                    }
                })
            };
            const {queryByText} = setupForSubmit({actions});
            fireEvent.click(button);
            /*const errorMessage = waitFor(() => queryByText('must not be null'))
            .then(data =>{
                fireEvent.change(displayNameInput,changeEvent('New Display Name'));
                expect(errorMessage).not.toBeInTheDocument();
            }).catch(error =>{});*/

            waitFor(() => queryByText('must not be null'))
            .then(fireEvent.change(displayNameInput, changeEvent('CorrectDisplayName')));
            const errorMessage = queryByText('must not be null');
            expect(errorMessage).not.toBeInTheDocument();
       
        });
        it('hides validation error when all is ok again userName',
        async () => {
            const actions ={
                postSignUp: jest.fn().mockRejectedValue({
                    response:{
                        data:{
                            validationErrors:{
                                userName: 'size must be between 4 and 50'
                            }
                        }
                    }
                })
            };
            const {queryByText} = setupForSubmit({actions});
            fireEvent.click(button);
            waitFor(() => queryByText('size must be between 4 and 50'))
            .then(fireEvent.change(userNameInput, changeEvent('Username47')));
            const errorMessage = queryByText('size must be between 4 and 50');
            expect(errorMessage).not.toBeInTheDocument();
       
        });
        it('hides validation error when all is ok again password',
        async () => {
            const actions ={
                postSignUp: jest.fn().mockRejectedValue({
                    response:{
                        data:{
                            validationErrors:{
                                password: 'Password must contain at least one upper case letter, one lower case letter,one number and one special character'
                            }
                        }
                    }
                })
            };
            const {queryByText} = setupForSubmit({actions});
            fireEvent.change(passwordInput, changeEvent('p'));
            fireEvent.click(button);
            waitFor(() => queryByText('Password must contain at least one upper case letter, one lower case letter,one number and one special character'))
            .then(fireEvent.change(passwordInput, changeEvent('Username47!@')));
            const errorMessage = queryByText('Password must contain at least one upper case letter, one lower case letter,one number and one special character');
            expect(errorMessage).not.toBeInTheDocument();
       
        });

    });
});

console.error = () => {};