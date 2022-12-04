import React from "react";
import Input from "../components/Input";

export class UserSignupPage extends React.Component{
    //stateful component
    
    state = {
        displayName: "",
        userName: "",
        password: "",
        confirmPassword: "",
        pendingAPiCall: false,
        errors: {},
        confirmPasswordMatch: true
    };
    
    onChangeDisplayName = (event) => {
        const value = event.target.value;
        const errors = {...this.state.errors};
        delete errors.displayName;
        this.setState({displayName: value});
    };
    onChangeUserName = (event) => {
        const value = event.target.value;
        const errors = {...this.state.errors};
        delete errors.userName;
        this.setState({userName: value});
    };
    onChangePassword = (event) => {
        const value = event.target.value;
        const passwordMatches = (this.state.confirmPassword === value);
        const errors = {...this.state.errors};
        delete  errors.password;
        errors.confirmPassword = passwordMatches ? '' : 'Passwords do not match'
        this.setState({password: value,confirmPasswordMatch: passwordMatches,errors});
    };
    onChangeConfirmPassword = (event) => {
        const value = event.target.value;
        const passwordMatches = (this.state.password === value);
        const errors = {...this.state.errors};
        errors.confirmPassword = passwordMatches ? '' : 'Passwords do not match'
        this.setState({confirmPassword: value, confirmPasswordMatch: passwordMatches,errors});
    };

    onClickSignUp =() => {
       // if(this.props.actions){
        const user ={
            userName: this.state.userName,
            displayName: this.state.displayName,
            password: this.state.password
        }
        this.setState({pendingAPiCall: true});
        this.props.actions.postSignUp(user).then(response => {
            this.setState({pendingAPiCall: false});
        }).catch((apiError) => {
            let errors = {...this.state.errors}
            if(apiError.response.data && apiError.response.data.validationErrors){
                errors = {...apiError.response.data.validationErrors}
            }
            this.setState({pendingAPiCall: false,errors});
        });
        
    }

    render(){
        return(
            <div className="container">
                <h1 className="text-center">Sign Up</h1>
                <div className="col-12 mb-13">
                    <Input label="Display Name"
                    placeholder="Your Display Name" 
                    value = {this.state.displayName}
                    onChange={this.onChangeDisplayName}
                    hasError = {this.state.errors.displayName && true}
                    error = {this.state.errors.displayName}
                    />
                    <div className="invalid-feedback">
                        {this.state.errors.displayName}
                    </div>
                </div>
                <div className="invalid-feedback">
                    {this.state.errors.displayName}
                </div>
                <div className="col-12 mb-13">
                    <Input label="Username"
                    placeholder="Your UserName"
                    value={this.state.userName}
                    onChange={this.onChangeUserName}
                    hasError = {this.state.errors.userName && true}
                    error = {this.state.errors.userName}/>
                </div>
                <div className="col-12 mb-13">
                    <Input label = "Password"
                    placeholder="Your Password" type="password"
                    value={this.state.password}
                    onChange={this.onChangePassword}
                    hasError = {this.state.errors.password && true}
                    error = {this.state.errors.password}/>
                </div>
                <div className="col-12 mb-13">
                    <Input label = "Confirm Password"
                    placeholder="Confirm Password" type="password"
                    value={this.state.confirmPassword}
                    onChange={this.onChangeConfirmPassword}
                    hasError = {this.state.errors.confirmPassword && true}
                    error = {this.state.errors.confirmPassword}/>
                </div>
                <div className="text-center">
                    <button className="btn btn-primary"
                    disabled = {this.state.pendingAPiCall || !this.state.confirmPasswordMatch}
                    onClick={this.onClickSignUp}>
                    {this.state.pendingAPiCall && (<div className="spinner-border  text-light spinner-border-sm mr-sm-1" role="status">
                        <span className="sr-only visually-hidden">Loading...</span>
                    </div>)}  
                    Sign Up</button>
                </div>
            </div>
        )
    }
}

UserSignupPage.defaultProps = {
    actions:{
        postSignUp: () => 
            new Promise((resolve,reject) => {
                resolve({});
            })
    }
}

export default UserSignupPage;