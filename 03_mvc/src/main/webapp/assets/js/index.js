/**
 * 
 */

const getContextPath = () => {
  const host = location.host; /* localhost:8080 */
  const url = location.href; /* http://localhost:8080/mvc/getDate.do */
  const begin = url.indexOf(host) + host.length;
  // host의 시작 위치에 host의 길이를 더하셈.
  // const end = url.indexOf('/'); // 이렇게 해버리면 첫번째 /가 나온다.
  const end = url.indexOf('/', begin + 1);
  // indexOf는 시작 위치를 정할 수 있다. 
  return url.substring(begin, end); // begin은 포함, end는 불포함.
  
}


location.host /* http://localhost:8080 */

location.href /* http://localhost:8080/mvc/getDate.do */

  const getDateTime = ()=>{
    const type = document.getElementById('type');
    if(type.value === 'date'){
      location.href = getContextPath() + '/getDate.do';
    } else if(type.value === 'time'){
      location.href === getContextPath() + '/getTime.do';
    } else if(type.value === 'datetime'){
      location.href === getContextPath() + '/getDateTime.do';
    }
  }
  
  document.getElementById('btn').addEventListener('click', getDateTime);