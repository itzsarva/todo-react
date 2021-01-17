import React, {useEffect, useRef, useState} from "react";
import '../../app.css';
import TodoList from "./TodoList";
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faListAlt, faCalendarPlus} from '@fortawesome/fontawesome-free-regular';
import '../../styles/todo/todo.css';
import AlertDialog from "../formUtils/AlertDialog";
import {get, post} from "../util/RestClient";
import {TODO_URL_API} from "../util/TodoConstants";

const Bucket = ({bucket, anchorOnclick, removeBucket}) => {
    return (
        <a href="#" className="btn btn-dark" onClick={() => anchorOnclick(event, bucket.id)}>
            <div style={{color: "white"}}>
                <button className="removeIcon" onClick={() => removeBucket(bucket.id)}>x</button>
                <FontAwesomeIcon icon={faListAlt} size={"5x"} color={"greenyellow"} className="todoIcon"/>
                <br/>
                {bucket.name}
            </div>
        </a>
    );
}

const NewBucket = ({addNewElement}) => {
    return (
        <a href="#" className="btn btn-dark" onClick={addNewElement}>
            <div style={{color: "white"}}>
                <FontAwesomeIcon icon={faCalendarPlus} size={"5x"} color={"greenyellow"} className="todoIcon"/>
                <br/>
                Add Todo
            </div>
        </a>
    );
}

const TodoBuckList = ({buckets, anchorOnclick, removeBucket}) => {
    return (buckets.map(bucket => {
        return <Bucket key={bucket.id} bucket={bucket} anchorOnclick={anchorOnclick}
                       removeBucket={removeBucket} className="todo-child"/>
    }));
}

const TodoBucket = () => {

    const [dialogueOpen, setDialogue] = useState(false);

    const [refresh, setPageRefresh] = useState(false);

    const [parentId, setParentId] = useState(3);

    const [parentName, setParentName] = useState(3);

    const addNewElement = (e) => {
        e.preventDefault();
        setDialogue(!dialogueOpen);
    }
    const [buckets, setBuckets] = useState([]);

    const [todos, setTodos] = useState([]);

    useEffect(() => {
        const username = JSON.parse(localStorage.getItem('user')).username;
        get(TODO_URL_API + 'get/todos/' + `${username}`).then(buckets => {
            setTodos(buckets[0].todoSet);
            setParentName(buckets[0].name);
            setBuckets(buckets);
        });
    }, [refresh]);

    const anchorOnclick = (e, id) => {
        e.preventDefault();
        setTodos(todos => [...buckets.find(buckets => buckets.id == id).todoSet]);
        setParentId(id);
        setParentName(buckets.find(buckets => buckets.id == id).name);
    }
    const todoInputRef = useRef();
    const inputElement = <input ref={todoInputRef}/>

    const createTodoBucket = () => {
        return {name: todoInputRef.current.value}
    }
    const submitTodo = () => {
        post(TODO_URL_API + 'add/bucket', createTodoBucket())
            .then(res => {
                setTodos([]);
                setDialogue(false);
                setPageRefresh(!refresh);
            });
    };

    const removeBucket = (id) => {
        post(TODO_URL_API + 'delete/bucket', {id: id})
            .then(res => {
                setPageRefresh(!refresh);
            });
    }

    return (<div>
        <AlertDialog handleClose={submitTodo} buttonText="Add Todo" bodyText={inputElement}
                     openDialog={dialogueOpen}
                     onBackdropClick={() => setDialogue(false)} headerText="Add Todo"/>
        <h3 style={{color: "black"}}>Hi, here is Your todo collections</h3>
        <br/>
        <div className="todo-container">
            {buckets.length > 0 ? <TodoBuckList buckets={buckets}
                                                removeBucket={removeBucket}
                                                anchorOnclick={anchorOnclick}/> : null}
            <NewBucket addNewElement={addNewElement}/>
        </div>
        <hr style={{background: "black"}}/>
        {buckets.length > 0 ?
            <TodoList parentId={parentId} parentName={parentName} todoList={todos} setPageRefresh={setPageRefresh}
                      refresh={refresh}/> : null}
    </div>);
};

export default TodoBucket;