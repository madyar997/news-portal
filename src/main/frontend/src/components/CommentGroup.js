import React from 'react';
import AddComment from './AddComment';
import Comment from './Comment';
import AuthService from '../services/authService'
import commentAPI from '../api/commentAPI';
import authHeader from '../services/authHeader';

class CommentGroup extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            comments:[],
            currentUser: null,
        }

    }

    componentDidMount() {
        const user = AuthService.getCurrentUser();
        if (user) {
            this.setState({
                currentUser: user
            });
        }
    }


    renderComments() {
        
        return this.props.comments.map(comment => {
            const { id, body, createdDate } = comment;
            const { username } = comment.user;
            return (
                <Comment key={id} body={body} username={username} />
            );
        });
    }

    
    render() {
        return (
            <div>
                {this.renderComments()}
                {this.state.currentUser ? <AddComment currentUser={this.state.currentUser} handleCommentSubmit={this.props.handleCommentSubmit} /> : null}
            </div>
        )
    }
}

export default CommentGroup;







