type ChildProps = { children?: React.ReactNode }

// Article
type ArticleInfo = {
  articleId: number,
  username: string,
  title: string,
  content: string,
  createdAt: string,
  updatedAt?: string,
  isWritten?: boolean
};

type PostArticle = {
  id? : string,
  title: string,
  content: string
}

type ReplyInfo = {

};

type PostReply = {

};

export const LOGIN_USER = "LOGIN_USER"
export const LOGOUT_USER = "LOGOUT_USER"
export const LOGIN_ADMIN = "LOGIN_ADMIN"

export type { ChildProps, ArticleInfo, PostArticle, ReplyInfo, PostReply };