import { Component } from "react";
import AuthService from '../services/authService';
import { Navbar,  Nav} from 'react-bootstrap';


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
            <Navbar bg="dark" variant="dark">
                <Navbar.Brand href="/">News Portal</Navbar.Brand>
                <Nav className="mr-auto">
                    <Nav.Link href="/">Home</Nav.Link>

                    <Nav.Link href="/about">About us</Nav.Link>
                </Nav>
                {currentUser && currentUser.roles.includes('ROLE_ADMIN') ?
                    <Nav>
                        <Nav.Link href="/admin">Admin</Nav.Link>
                    </Nav>
                    : null
                 }
                {currentUser ? <Nav>
                    <Nav.Link href="/profile">Profile</Nav.Link>
                    <Nav.Link href="/signout" onClick={this.logOut}>Sign out</Nav.Link>
                </Nav> :
                    <Nav>
                        <Nav.Link href="/login">Sign in</Nav.Link>
                        <Nav.Link href="/register"> Register</Nav.Link>
                    </Nav>}
            </Navbar>


            // <div className='ui container' >
            //     <div className='ui menu'>
            //         <Link to={'/'}>
            //             <li className='ui item'>Home</li>
            //         </Link>

            //         {currentUser && (
            //             <div className='right menu'>
            //                 <li className='ui item'>
            //                     <Link to={"/profile"}>User</Link>
            //                 </li>
            //             </div>

            //         )}

            //         {currentUser ? (
            //             <div className='right menu'>
            //                 <li className='ui item'>
            //                     <a href='/logout' onClick={this.logOut}>Log out</a>
            //                 </li>
            //             </div>
            //         ) : (
            //             <div className='right menu'>
            //                 <li className='ui item'>
            //                     <Link to={'/login'}>Log in</Link>
            //                 </li>
            //                 <li className='ui item'>
            //                     <Link to={'/register'}>Register</Link>
            //                 </li>
            //             </div>
            //         )}
            //     </div>

            // </div>
        );
    };
}

export default Header;