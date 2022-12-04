import axios from 'axios';
import * as apiCalls from './apiCalls';

describe('apiCalls',() =>{
    describe('sigunp',() => {
        it('calls api/v1/users',()=>{
            const mockSignUp = jest.fn();
            axios.post = mockSignUp;
            apiCalls.signUp();
            const path = mockSignUp.mock.calls[0][0];
            expect(path).toBe('/api/v1/users');
        });
    });
});