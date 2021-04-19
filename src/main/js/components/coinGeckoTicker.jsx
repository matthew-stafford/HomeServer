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
		
		this.updateData = this.updateData.bind(this);

	}
	
	// update from child once a coin has been added to springboot database
	updateData(coin) {
		let obj = {name: coin.name, geckoId: coin.geckoId};
		let data = this.state.data;
		
		data._embedded.coinGeckoTickers.push(obj);
		
		this.setState({data:data});
	}
	
	// grab data from springboot database for which coins to display
	fetchData () {
		fetch("/api/coinGeckoTickers")
	    .then(res => res.json())
	    .then(
	      (result) => {	
	        this.setState({
	          isLoaded: true,
	          data: result

	        });
	      },
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
											
											<tr key={item.geckoId}>
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
				    <CoinGeckoTickerForm updateData={this.updateData}/>
				  </div>
				</div>
			</div>
		)
	}
	
}

export default CoinGeckoTicker;