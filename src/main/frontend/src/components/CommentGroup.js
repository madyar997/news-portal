import React from 'react';
import AddComment from './AddComment';
import Comment from './Comment';
import AuthService from '../services/authService'

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
        console.log(this.props.comments)
        return this.props.comments.map(comment => {
                        return (
                <Comment key={comment.id} comment={comment} />
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







