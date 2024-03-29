import styles from './Loading.module.css';

const Loading = function ({text}) {
  return (
    <div className={styles.loading_container}>
      <img className={styles.loading_img} src="/imgs/ch1_bol_normal_run.gif" alt="loading_img" />
      <div className={styles.loading_txt} >{text}</div>
    </div>
  )
}

export default Loading;