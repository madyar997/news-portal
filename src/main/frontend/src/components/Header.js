import { Component } from "react";
import { Link } from "react-router-dom";
import AuthService from '../services/authService';


class Header extends Component {
    constructor(props) {
        super(props);
        this.logOut = this.logOut.bind(this);
        this.state = {
            currentUser: undefined,
        };
    }

    componentDidMount() {
        const user = AuthService.getCurrentUser();

        if (user) {
            this.setState({
                currentUser: user
            });
        }
    }

    logOut() {
        AuthService.logout();
    }

    render() {
        const { currentUser } = this.state;
        return (

            <div className='ui container' >
                <div className='ui menu'>
                    <Link to={'/'}>
                        <li className='ui item'>Home</li>
                    </Link>

                    {currentUser && (
                        <div className='right menu'>
                            <li className='ui item'>
                                <Link to={"/user"}>User</Link>
                            </li>
                        </div>

                    )}

                    {currentUser ? (
                        <div className='right menu'>
                            <li className='ui item'>
                                <a href='/logout' onClick={this.logOut}>Log out</a>
                            </li>
                        </div>
                    ) : (
                        <div className='right menu'>
                            <li className='ui item'>
                                <Link to={'/login'}>Log in</Link>
                            </li>
                            <li className='ui item'>
                                <Link to={'/register'}>Register</Link>
                            </li>
                        </div>
                    )}
                </div>

            </div>
        );
    };
}

export default Header;