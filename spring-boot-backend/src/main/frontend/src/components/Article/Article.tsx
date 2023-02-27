import { useNavigate } from "react-router-dom";
import { ArticleInfo } from "../../utility/types";
import {useContext} from "react";
import authContext from "../../store/auth-context";

type Props = { item:ArticleInfo, onDelete: (id:string) => void }

const Article:React.FC<Props> = (props) => {

  let navigate = useNavigate();

  const id = props.item!.articleId.toString();

  const authCtx = useContext(authContext);

  const backHandler = (event: React.MouseEvent<HTMLButtonElement>) => {
    event.preventDefault();
    navigate("/page/1");
  }
  
  const updateHandler = (event: React.MouseEvent<HTMLButtonElement>) => {
    event.preventDefault();
    navigate("../update/" + id);
  }

  const deleteHandler = (event: React.MouseEvent<HTMLButtonElement>) => {
    event.preventDefault();
    if (window.confirm("삭제하시겠습니까?")){
      props.onDelete(id);
    }
  }

  return (
    <div>
      <header>
          <h4>{props.item!.title}</h4>
          <div><span>{props.item!.content}</span></div>
          <div>
            <span>이름: {props.item!.username}</span><br />
            <span>날짜 : {props.item!.updatedAt}</span>
          </div>
        </header>
        <button onClick={backHandler}>뒤로</button>
        {/* {props.item!.isWritten &&  */}
          <div>
            <button disabled={props.item.username==authCtx.userObj.username ?  false: true } onClick={updateHandler}>수정</button><br />
            <button disabled={props.item.username==authCtx.userObj.username ?  false: true } onClick={deleteHandler}>삭제</button>
          </div>
        {/* } */}
    </div>
  );
}

export default Article;