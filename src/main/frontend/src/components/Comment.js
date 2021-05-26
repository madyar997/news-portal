import { Component } from "react";

class Comment extends Component {
    constructor(props) {
        super(props);
    }
    render() {
        const { body } = this.props;
        return (
            <div className='comment card mb-2'>
                <div className='card-body'>
                    <strong>Madyar Turgenbayev</strong>2 hours ago <br />{body}
                </div>
            </div>
        )
    }
}
export default Comment;