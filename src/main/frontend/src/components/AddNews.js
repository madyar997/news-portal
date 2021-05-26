import React from 'react';
import SunEditor from 'suneditor-react';
import 'suneditor/dist/css/suneditor.min.css';


class AddNews extends React.Component {
    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
    }

    state = {
        title: '',
        text: '',
        createdDate: ''
    }

    handleChange(content) {
        this.setState({ text: content });
        console.log(this.state);
    }

    add = (e) => {
        e.preventDefault();
        if (this.state.title === '' || this.state.text === '') {
            alert('All the fields are mandatory')
            return;
        }
        this.setState({ createdDate: new Date() });
        this.props.addNewsHandler(this.state);
        this.setState({ title: '', text: '', createdDate: '' });
        this.props.history.push('/');
    }

    render() {
        return (
            <div className='ui main'>
                <h2>Add News </h2>

                <form className='ui form' onSubmit={this.add}>
                    <div className='field'>
                        <label>Title</label>
                        <input type='text' name='title' value={this.state.title} placeholder='Title' onChange={(e) => this.setState({ title: e.target.value })} />
                    </div>
                    <div className='field'>
                        <label>Content</label>
                        <SunEditor height='300px' onChange={this.handleChange} />
                    </div>
                    <button className='ui button blue'>Add</button>
                </form>
            </div>
        );
    }
}

export default AddNews;