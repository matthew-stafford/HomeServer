import React from 'react';
import CoinGeckoTickerForm from './coinGeckoTickerForm.jsx';

class CoinGeckoTicker extends React.Component {
	
	constructor(props) {
		super(props);
		
		this.state = {
			data: {},
			error: null,
			isLoaded: false
		}
	}
	
	
	fetchData () {
		fetch("/api/coinGeckoTickers")
	    .then(res => res.json())
	    .then(
	      (result) => {
			console.log(result); 		
	        this.setState({
	          isLoaded: true,
	          data: result

	        });
	      },
	      // Note: it's important to handle errors here
	      // instead of a catch() block so that we don't swallow
	      // exceptions from actual bugs in components.
	      (error) => {
			console.log(error);
	        this.setState({
	          isLoaded: true,
	          error
	        });
	      }
	    )
	}
	
	componentDidMount() {
		this.fetchData();
	}
	
	render() {
		return (			
			<div id="coinGeckoTicker">
				<div className="card">
				  <div className="card-body">
				    <h5 className="card-title">Coin Gecko</h5>
	
					{
						this.state.isLoaded && this.state.data._embedded.coinGeckoTickers.length > 0 ?			
							<table className="table table-condensed">
								<thead>
									<tr>
								    	<th scope="col">Name</th>
								    	<th scope="col">USD</th>
								    	<th scope="col">BTC</th>
								    	<th scope="col">24h %</th>
									</tr>
								</thead>
								<tbody>{
										this.state.data._embedded.coinGeckoTickers.map( (item, index) =>
											
											<tr key={item.id}>
												<td>{item.name}</td>
												<td></td>
												<td></td>
												<td></td>
											</tr>
										 )
									}
								</tbody>
							</table>	
							: <p>Get started using the Coin Gecko price ticker by adding a coin</p>
					}
				    <CoinGeckoTickerForm/>
				  </div>
				</div>
			</div>
		)
	}
	
}

export default CoinGeckoTicker;